import glob
import pickle

import cv2
import numpy as np


def reg_log(kmeans1, nb_k):
	list_images = glob.glob("compared_images/*.jpg")

	descriptors = np.empty(shape=(0, 128), dtype=float)  # array of all descriptors from all images
	dim_image = []  # nb of descriptors per file

	for s in list_images:
		print("###", s, "###")
		image = cv2.imread(s)
		gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
		sift = cv2.xfeatures2d.SIFT_create()
		keypoints, descriptor = sift.detectAndCompute(gray, None)
		image = cv2.drawKeypoints(gray, keypoints, image, flags=cv2.DRAW_MATCHES_FLAGS_DRAW_RICH_KEYPOINTS)
		cv2.imwrite("res/reco_" + s, image)
		dim_image.append(descriptor.shape[0])
		descriptors = np.append(descriptors, descriptor, axis=0)

	# BOW initialization
	bows = np.empty(shape=(0, nb_k), dtype=float)

	k = kmeans1.predict(descriptors)

	# writing the BOWs for second k-means
	i = 0
	for nb in dim_image:  # for each image
		tmp_bow = [0] * nb_k
		j = 0
		while j < nb:  # for each descriptor of this image
			tmp_bow[k[i]] += 1
			j += 1
			i += 1
		copy_bow = tmp_bow.copy()
		bows = np.append(bows, [copy_bow], 0)

	print("nb of descriptors per file : ", dim_image)
	# print("BOWs : ", bows)

	# chargement de l'objet
	with open('sauvegarde.logr', 'rb') as input:
		logistic_regr = pickle.load(input)

	# calcul des labels pŕedits
	print("Predict : ", logistic_regr.predict(bows))
